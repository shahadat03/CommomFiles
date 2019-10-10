    public static void deleteAllCacheFile(Context context) {
        ArrayList<String> allFile = getOfflineList(context, ALL_FILE);
        for (int i = 0; i < allFile.size(); i++) {
            //context.deleteFile(allFile.get(i));

            saveOffline(context, allFile.get(i), null);
        }
    }

    public static void deleteCacheFile(Context context, String file) {
        context.deleteFile(file);
    }

    public static <CLASS> void saveOffline(Context context, String file, CLASS data) {

        try {
            ArrayList<String> allFiles = getOfflineList(context, ALL_FILE);
            if (allFiles != null) {
                if (!allFiles.contains(file)) {
                    allFiles.add(file);
                    if (!file.equals(ALL_FILE)) {
                        saveOffline(context, ALL_FILE, allFiles);
                    }
                }
            } else {
                allFiles = new ArrayList<>();
                allFiles.add(ALL_FILE);
                saveOffline(context, ALL_FILE, allFiles);
            }

        } catch (Exception e) {
        }

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = context.openFileOutput(file, MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = null;
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(data);
            objectOutputStream.close();
        } catch (Exception e) {
            int i =0;
//            Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show();
        }
    }

    public static <CLASS> CLASS getOfflineSingle(Context context, String file) {

        CLASS items = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = context.openFileInput(file);
            ObjectInputStream objectInputStream = null;
            objectInputStream = new ObjectInputStream(fileInputStream);
            items = (CLASS) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception e) {
//            Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show();
        }
        return items;
    }

    public static <CLASS> ArrayList<CLASS> getOfflineList(Context context, String file) {

        ArrayList<CLASS> items = new ArrayList<>();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = context.openFileInput(file);
            ObjectInputStream objectInputStream = null;
            objectInputStream = new ObjectInputStream(fileInputStream);
            items = (ArrayList<CLASS>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception e) {
//            Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show();
        }
        if(items==null){
            return new ArrayList<>();
        }
        return items;
    }