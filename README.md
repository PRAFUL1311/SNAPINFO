# Snap InfoApp

Snap InfoApp is a mobile application that uses Google's ML Kit libraries to provide real-time information about objects, text, and barcodes through your device's camera. It includes features for object recognition, text extraction, and barcode scanning.

## Features

- **Object Recognition:** Identify and retrieve information about objects in real-time.
- **Text Recognition:** Extract and translate text from images.
- **Barcode Scanning:** Scan and decode barcodes to get product details and other relevant information.
- **Multi-Language Support:** Translate recognized text into various languages.

## Prerequisites

- **Android Studio:** Ensure you have Android Studio installed.
- **Google Play Services:** Required for ML Kit functionality.
- **Firebase Project:** Create a Firebase project and enable ML Kit APIs.

## Installation

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/yourusername/snap-infoapp.git
Open the Project:

Open Android Studio and select "Open an existing Android Studio project," then navigate to the cloned repository directory.

Add Firebase Configuration:

Go to the Firebase Console.
Create a new Firebase project or use an existing one.
Add an Android app to your Firebase project and download the google-services.json file.
Place the google-services.json file in the app/ directory of your project.
Add Dependencies:

Open build.gradle (Project) and add the Google services classpath:

groovy
Copy code
buildscript {
    dependencies {
        classpath 'com.google.gms:google-services:4.3.15' // Check for latest version
    }
}
Open build.gradle (App) and apply the Google services plugin at the bottom:

groovy
Copy code
apply plugin: 'com.google.gms.google-services'
Add ML Kit dependencies to build.gradle (App):

groovy
Copy code
dependencies {
    implementation 'com.google.mlkit:barcode-scanning:18.2.0' // Check for latest version
    implementation 'com.google.mlkit:text-recognition:18.0.0' // Check for latest version
    implementation 'com.google.mlkit:object-detection:18.1.2' // Check for latest version
}
Sync Project:

Click "Sync Now" in Android Studio to sync your project with the updated Gradle files.

Usage
Object Recognition
To use the Object Recognition API:

java
Copy code
import com.google.mlkit.vision.objects.ObjectDetection;
import com.google.mlkit.vision.objects.ObjectDetector;
import com.google.mlkit.vision.objects.ObjectDetectorOptions;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.objects.DetectedObject;

// Initialize ObjectDetector
ObjectDetectorOptions options = new ObjectDetectorOptions.Builder()
        .setDetectorMode(ObjectDetectorOptions.STREAM_MODE)
        .enableClassification() // Optional
        .build();
ObjectDetector objectDetector = ObjectDetection.getClient(options);

// Create an InputImage object from a bitmap
InputImage image = InputImage.fromBitmap(bitmap, rotationDegree);

// Process the image
objectDetector.process(image)
    .addOnSuccessListener(detectedObjects -> {
        for (DetectedObject detectedObject : detectedObjects) {
            // Handle detected objects
            String objectName = detectedObject.getLabels().get(0).getText();
            // Use objectName as needed
        }
    })
    .addOnFailureListener(e -> {
        // Handle error
    });
Text Recognition
To use the Text Recognition API:

java
Copy code
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;

// Initialize TextRecognizer
TextRecognizer textRecognizer = TextRecognition.getClient();

// Create an InputImage object from a bitmap
InputImage image = InputImage.fromBitmap(bitmap, rotationDegree);

// Process the image
textRecognizer.process(image)
    .addOnSuccessListener(text -> {
        // Handle successful text recognition
        String recognizedText = text.getText();
        // Display or use recognized text
    })
    .addOnFailureListener(e -> {
        // Handle error
    });
Barcode Scanning
To use the Barcode Scanning API:

java
Copy code
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.barcode.Barcode;

// Initialize BarcodeScanner
BarcodeScannerOptions options = new BarcodeScannerOptions.Builder()
        .setFormats(Barcode.FORMAT_ALL_FORMATS)
        .build();
BarcodeScanner barcodeScanner = BarcodeScanning.getClient(options);

// Create an InputImage object from a bitmap
InputImage image = InputImage.fromBitmap(bitmap, rotationDegree);

// Process the image
barcodeScanner.process(image)
    .addOnSuccessListener(barcodes -> {
        for (Barcode barcode : barcodes) {
            // Handle barcode information
            String rawValue = barcode.getRawValue();
            // Use rawValue as needed
        }
    })
    .addOnFailureListener(e -> {
        // Handle error
    });
Contributing
Contributions are welcome! If you have suggestions or improvements, please open an issue or submit a pull request.

License
This project is licensed under the MIT License - see the LICENSE file for details.

Contact
For any questions or feedback, please contact:

Email: Bombatkarpraful11@gmail.com
GitHub:Praful_1311

