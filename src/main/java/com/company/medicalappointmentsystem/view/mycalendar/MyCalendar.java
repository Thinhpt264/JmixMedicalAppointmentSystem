package com.company.medicalappointmentsystem.view.mycalendar;


import com.company.medicalappointmentsystem.entity.Task;
import com.company.medicalappointmentsystem.entity.User;
import com.company.medicalappointmentsystem.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.router.Route;
import io.jmix.core.Messages;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.flowui.DialogWindows;
import io.jmix.flowui.component.combobox.JmixComboBox;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;
import io.jmix.fullcalendarflowui.component.FullCalendar;
import io.jmix.fullcalendarflowui.component.data.EntityCalendarEvent;
import io.jmix.fullcalendarflowui.component.event.DayCellClassNamesContext;
import io.jmix.fullcalendarflowui.component.event.DayHeaderClassNamesContext;
import io.jmix.fullcalendarflowui.component.event.EventClickEvent;
import io.jmix.fullcalendarflowui.component.model.DayOfWeek;
import io.jmix.fullcalendarflowui.kit.component.model.CalendarDisplayModes;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static io.jmix.fullcalendarflowui.kit.component.model.CalendarDisplayModes.DAY_GRID_MONTH;

@Route(value = "my-calendar", layout = MainView.class)
@ViewController(id = "MyCalendar")
@ViewDescriptor(path = "my-calendar.xml")
public class MyCalendar extends StandardView {
    @ViewComponent
    private CollectionLoader<Task> tasksDl;

    @ViewComponent
    private JmixComboBox<CalendarDisplayModes> displayModesBox;
    @ViewComponent
    private H4 calendarTitle;


    @Autowired
    private CurrentAuthentication currentAuthentication;

    @ViewComponent
    private FullCalendar calendar;
    @Autowired
    private Messages messages;
    @Autowired
    private DialogWindows dialogWindows;

    private static final String TODAY_CLASS_NAME = "uisamples-today";
    private static final String WEEKEND_CLASS_NAME = "uisamples-weekend";
    private static final String DAY_HEADER_CLASS_NAME = "uisamples-day-header";


    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        final User user = (User) currentAuthentication.getUser();
//        tasksDl.setParameter("user", user);
        tasksDl.load();
    }

    @Subscribe(id = "prevBtn", subject = "clickListener")
    public void onPrevBtnClick(final ClickEvent<JmixButton> event) {
        calendar.navigateToPrevious();
    }

    @Subscribe(id = "homeBtn", subject = "clickListener")
    public void onHomeBtnClick(final ClickEvent<JmixButton> event) {
        calendar.navigateToToday();
    }

    @Subscribe(id = "nextBtn", subject = "clickListener")
    public void onNextBtnClick(final ClickEvent<JmixButton> event) {
        calendar.navigateToNext();
    }

    @Subscribe
    public void onInit(final InitEvent event) {
        calendarTitle.setText("Lịch Làm Việc");
        displayModesBox.setItems(DAY_GRID_MONTH,
                CalendarDisplayModes.TIME_GRID_WEEK,
                CalendarDisplayModes.TIME_GRID_DAY,
                CalendarDisplayModes.LIST_WEEK,
                CalendarDisplayModes.LIST_DAY,
                CalendarDisplayModes.LIST_MONTH,
                CalendarDisplayModes.LIST_YEAR);
        displayModesBox.setItemLabelGenerator(mode ->
                messages.getMessage("CalendarDisplayModes." + mode.name()));
        displayModesBox.setValue(DAY_GRID_MONTH);

        displayModesBox.addValueChangeListener(e ->
                calendar.setCalendarDisplayMode(e.getValue() == null ? DAY_GRID_MONTH : e.getValue()));
    }

    @Subscribe("calendar")
    public void onCalendarEventClick(final EventClickEvent event) {
        EntityCalendarEvent<Task> entityCalendarEvent = event.getCalendarEvent();
        dialogWindows.detail(this, Task.class)
                .editEntity(entityCalendarEvent.getEntity())
                .open();
    }

    @Install(to = "calendar", subject = "dayCellClassNamesGenerator")
    private List<String> calendarDayCellClassNamesGenerator(final DayCellClassNamesContext context) {
        List<String> classes = new ArrayList<>(2);

        DayOfWeek dow = context.getDayOfWeek();
        if (dow == DayOfWeek.SATURDAY || dow == DayOfWeek.SUNDAY) {
            classes.add(WEEKEND_CLASS_NAME);
        }
        if (context.getDate().isEqual(LocalDate.now())) {
            classes.add(TODAY_CLASS_NAME);
        }
        return classes;
    }

    @Install(to = "calendar", subject = "dayHeaderClassNamesGenerator")
    private List<String> calendarDayHeaderClassNamesGenerator(final DayHeaderClassNamesContext context) {
        List<String> classes = new ArrayList<>(2);
        classes.add(DAY_HEADER_CLASS_NAME);

        DayOfWeek dow = context.getDayOfWeek();
        if (dow == DayOfWeek.SATURDAY || dow == DayOfWeek.SUNDAY) {
            classes.add(WEEKEND_CLASS_NAME);
        }
        if (dow == DayOfWeek.fromDayOfWeek(LocalDate.now().getDayOfWeek())) {
            classes.add(TODAY_CLASS_NAME);
        }
        return classes;
    }

}