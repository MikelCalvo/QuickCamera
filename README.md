# QuickCamera    [![](https://jitpack.io/v/net.mikelcalvo/QuickCamera.svg)](https://jitpack.io/#net.mikelcalvo/QuickCamera)

QuickCamera is a library built with Kotlin on top of the library [CameraView](https://github.com/natario1/CameraView) to offer a quick way to launch a camera with a light theme inside your app

## How to implement it
First, check if you have jitpack added to your build.gradle
It has to be something like this:

````

allprojects {
  repositories {
    ...
      maven { url 'https://jitpack.io' }
  }
}
  
````  

Then you will have to add the library to your dependencies like this:

````
implementation 'net.MikelCalvo:QuickCamera:v1.0.1'
````

## How to use QuickCamera
### Step 1: Prepare the QuickCamera setup parameters
You can include the parameters you want like this:

```
QuickCameraSetup.setCameraFlash("auto");
```

If you don't set any parameters, it will use the default ones.

These are the parameters that the camera accepts:

| Parameter     | Options  | Default Value  | Method (Java)  | Method (Kotlin)  |
| :-----:       | :------: | :------------: | :------------: | :--------------: |
| Flash         | on, off, auto, torch | off | .setCameraFlash("STRING"); | .cameraFlash = "STRING"
| White Balance | auto, incasdescent, daylight, cloudy | auto | .setCameraWhiteBalance("STRING"); | .cameraWhiteBalance = "STRING"
| HDR           | on, off | on | .setCameraHDR("STRING"); | .cameraHDR = "STRING"
| Camera Size   | square, full | square | .setCameraSize("STRING"); | .cameraSize = "STRING"
| Toolbar Title | Any Text | "Camera" (Translations coming soon) | .setCameraToolbarTitle("STRING"); | .cameraToolbarTitle = "STRING"
| Bitmap Quality Percentaje | from 1 to 100 | 100 | .setPictureQualityPercentage(INT); | .pictureQualityPercentage = INT

NOTE: If your apps style does not include a toolbar, we will not show one.

### Step 2: Launch QuickCamera
Once you have the configuration for your camera, launching it is as simple as this:
```
QuickCameraSetup.launch(this);  //"this" is the context
```

### Step 3: Get the captured image

You have to insert the following method in your activity onResume method:
```
    @Override
    protected void onResume() {
        Bitmap resultImage = QuickCameraBitmap.getImage();
        super.onResume();
    }
```

If you like this library, feel free to make any contributions to it.

## QuickCamera License
QuickCamera is [Do What The F*ck You Want To Public License](https://github.com/MikelCalvo/QuickCamera/blob/master/LICENSE)

## CameraView License (Third-Party)
CameraView is [MIT License](https://github.com/natario1/CameraView/blob/master/LICENSE)
