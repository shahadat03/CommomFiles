   implementation 'com.linchaolong.android:imagepicker:1.5'

int PROFILE_PIC_IMAGE = 23;
public void selectProfilePic(int type) {

        //<editor-fold desc="Camera Permission">
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,
                        Manifest.permission.CAMERA)) {
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PROFILE_PIC_IMAGE);
                } else {
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PROFILE_PIC_IMAGE);
                }
                return;

            }
        }
        //</editor-fold>

        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.System.canWrite(this)) {
                requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, type);
            } else {
                pickImage(type); // method
            }
        } else {
            pickImage(type);
        }

    }

    private void pickImage(int type) {
        if (type == PROFILE_PIC_IMAGE) {
            imagePicker = new ImagePicker();
            imagePicker.startChooser(this, new ImagePicker.Callback() {
                @Override
                public void onPickImage(Uri imageUri) {

                }

                @Override
                public void onCropImage(Uri imageUri) {
                    b.b.profilePic.setImageURI(imageUri);
                    File accualProfileFile = new File(imageUri.getPath());
                    profilePicFile = accualProfileFile;

                    imageBitmap = BitmapFactory.decodeFile(profilePicFile.getPath());


                }

                @Override
                public void cropConfig(CropImage.ActivityBuilder builder) {
                    builder
                            .setMultiTouchEnabled(false)
                            .setOutputCompressQuality(100)
                            .setGuidelines(CropImageView.Guidelines.OFF)
                            .setCropShape(CropImageView.CropShape.RECTANGLE)
                            .setRequestedSize(1500, 1500)
                            .setAspectRatio(9, 9);
                }

                @Override
                public void onPermissionDenied(int requestCode, String[] permissions,
                                               int[] grantResults) {

                }
            });
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        switch (requestCode) {
            case PROFILE_PIC_IMAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImage(PROFILE_PIC_IMAGE);
                } else {
                    try {
                        Snackbar snackbar = Snackbar.make(b.getRoot(), getResources().getString(R.string.message_no_camera_permission_snackbar), 4000);
                        snackbar.setAction(getResources().getString(R.string.allow), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                                intent.setData(uri);
                                startActivity(intent);
                            }
                        });
                        snackbar.show();
                        break;
                    } catch (Exception e) {

                    }
                }
                return;
            }


        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        try {
            super.onActivityResult(requestCode, resultCode, data);

            imagePicker.onActivityResult(this, requestCode, resultCode, data);
        } catch (Exception e) {
            Toast.makeText(context, "Something went wrong, Please try again", Toast.LENGTH_SHORT).show();
        }

    }

}