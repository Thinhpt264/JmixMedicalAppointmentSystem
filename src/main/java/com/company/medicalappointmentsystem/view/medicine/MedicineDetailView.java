package com.company.medicalappointmentsystem.view.medicine;

import com.company.medicalappointmentsystem.entity.Medicine;
import com.company.medicalappointmentsystem.view.main.MainView;

import com.vaadin.flow.component.upload.SucceededEvent;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.core.FileRef;

import io.jmix.core.FileStorage;
import io.jmix.core.FileStorageLocator;
import io.jmix.flowui.Notifications;

import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.io.InputStream;
import java.util.ArrayList;

@Route(value = "medicines/:id", layout = MainView.class)
@ViewController(id = "Medicine.detail")
@ViewDescriptor(path = "medicine-detail-view.xml")
@EditedEntityContainer("medicineDc")
public class MedicineDetailView extends StandardDetailView<Medicine> {

    @Autowired
    private FileStorageLocator fileStorageLocator;

    @Autowired
    private DataManager dataManager;

    @Autowired
    private Notifications notifications;


//    public void onUploadSuccess(FileUploadSucceededEvent<JmixUpload>  event) {
//        JmixUpload uploadField = event.getSource();
//        MultiFileMemoryBuffer buffer = (MultiFileMemoryBuffer) uploadField.getReceiver();
//
//        if (buffer != null) {
//            Medicine medicine = getEditedEntity();
//
//            // Nếu thuốc chưa được lưu, lưu trước
//            if (medicine.getId() == null) {
//                medicine.setName("default");
//                medicine.setDescription("default");
//                medicine.setDosage("default");
//                medicine.setCountryOfProduction("default");
//                medicine.setOriginBrand("default");
//                medicine.setRegistrationNumber("default");
//                medicine.setPrice(2000.000);
//
//                medicine = dataManager.save(medicine);
//            }
//
//            List<MedicineImage> newImages = new ArrayList<>();
//
//            for (String fileName : buffer.getFiles()) {
//                try (InputStream inputStream = buffer.getInputStream(fileName)) {
//                    FileStorage fileStorage = fileStorageLocator.getDefault();
//                    FileRef fileRef = fileStorage.saveStream(fileName, inputStream);
//
//                    MedicineImage image = dataManager.create(MedicineImage.class);
//                    image.setImageFile(fileRef);
//                    image.setMedicine(medicine);
//
//                    newImages.add(image);
//                } catch (Exception e) {
//                    notifications.create("Failed to upload " + fileName + ": " + e.getMessage())
//                            .withType(Notifications.Type.ERROR)
//                            .show();
//                }
//            }
//
//            // Nếu có ảnh mới được thêm, lưu vào database
//            if (!newImages.isEmpty()) {
//                dataManager.save(newImages);
//
//                // Cập nhật danh sách ảnh của thuốc
//                if (medicine.getImages() == null) {
//                    medicine.setImages(new ArrayList<>());
//                }
//                medicine.getImages().addAll(newImages);
//
//                // Cập nhật UI ngay lập tức
//                medicineImagesDc.getMutableItems().addAll(newImages);
//
//                notifications.create("Uploaded " + newImages.size() + " images successfully")
//                        .withType(Notifications.Type.SUCCESS)
//                        .show();
//            }
//        } else {
//            notifications.create("Failed to upload images")
//                    .withType(Notifications.Type.ERROR)
//                    .show();
//        }
//    }










}