package com.hajdugellert.recipeproject.vision;

import com.google.protobuf.ByteString;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.google.cloud.vision.v1.*;
import com.google.cloud.vision.v1.Feature.Type;


import java.util.ArrayList;
import java.util.List;

@Component
public class GoogleVisionClient implements IngredientDetectionClient{
    @Override
    public List<String> detectLabels(MultipartFile image)
    { try(ImageAnnotatorClient visionClient = ImageAnnotatorClient.create())
    {

        ByteString imgBytes = ByteString.copyFrom(image.getBytes());
        // Builds the image annotation request
        List<AnnotateImageRequest> requests = new ArrayList<>();
        Image img = Image.newBuilder().setContent(imgBytes).build();
        Feature feat = Feature.newBuilder().setType(Type.LABEL_DETECTION).setMaxResults(15).build();
        AnnotateImageRequest request =
                AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
        requests.add(request);

        // Performs label detection on the image file
        BatchAnnotateImagesResponse response = visionClient.batchAnnotateImages(requests);
        List<AnnotateImageResponse> responses = response.getResponsesList();
        List<String> labels = new ArrayList<>();

        for (AnnotateImageResponse res : responses) {
            if (res.hasError()) {
                throw new RuntimeException(
                        "Google Vision API error: " + res.getError().getMessage()
                );
            }

            for (EntityAnnotation annotation : res.getLabelAnnotationsList()) {
                labels.add(annotation.getDescription().toLowerCase());
            }
        }

        return labels.stream().distinct().toList();

    }
    catch (Exception e )
    {
        throw new RuntimeException("Failed to analyze image with Google Vision", e);
    }




    }


}
