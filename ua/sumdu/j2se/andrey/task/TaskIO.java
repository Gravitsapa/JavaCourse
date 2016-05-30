package ua.sumdu.j2se.andrey.task;

import java.io.*;
import java.util.Date;

public class TaskIO {
    public static void write(TaskList tasks, OutputStream out) throws IOException {
        DataOutputStream outputStream = new DataOutputStream(out);
        try {
            outputStream.writeInt(tasks.size());
            for (Task t : tasks) {
                outputStream.writeInt(t.getTitle().length());
                outputStream.writeUTF(t.getTitle());
                outputStream.writeBoolean(t.isActive());
                outputStream.writeLong(t.getRepeatInterval());
                if (t.isRepeated()) {
                    outputStream.writeLong(t.getStartTime().getTime());
                    outputStream.writeLong(t.getEndTime().getTime());
                } else {
                    outputStream.writeLong(t.getTime().getTime());
                }
            }
        } finally {
            outputStream.flush();
            outputStream.close();
        }
    }


    public static void read(TaskList tasks, InputStream in) throws IOException {
        try (DataInputStream inputStream = new DataInputStream(in)) {
            int tasksCount = inputStream.readInt();
            for (int i = 0; i < tasksCount; i++) {
                //int len = inputStream.readInt();
                String title = inputStream.readUTF();
                boolean activity = inputStream.readBoolean();
                long interval = inputStream.readInt();
                Date startTime = new Date(inputStream.readLong());
                Task tempTask;
                if (interval > 0) {
                    Date endTime = new Date(inputStream.readLong());
                    tempTask = new Task(title, startTime, endTime, (int) interval);
                } else {
                    tempTask = new Task(title, startTime);
                }
                tempTask.setActive(activity);
                tasks.add(tempTask);
            }
        }
    }

    void writeBinary(TaskList tasks, File file) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            write(tasks, fos);
        }
    }

    void readBinary(TaskList tasks, File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            read(tasks, fis);
        }
    }

    public static void write(TaskList tasks, Writer out) throws IOException {
        BufferedWriter bw = new BufferedWriter(out);
        try {
            int max = tasks.size() - 1;
            int i = 0;
            for (Task t : tasks) {
                bw.append(t.toString());
                bw.append((i != max ? ";" : "."));
                bw.newLine();
                i++;
            }
        } finally {
            bw.flush();
        }
    }

//    public static void read(TaskList tasks, Reader in) throws IOException {
//        try (BufferedReader br = new BufferedReader(in)) {
//            if (br.ready()) {
//                String line;
//                while ((line = br.readLine()) != null) {
//                    tasks.add(makeTaskFromString(line));//TODO create method
//                    if (line.charAt(line.length() - 1) == '.') {
//                        break;
//                    }
//                }
//            } else {
//                throw new IOException("Stream is not ready to be read!");
//            }
//        }
//    }


    public static void writeText(TaskList tasks, File file) throws IOException {
        try (FileWriter fw = new FileWriter(file)) {
            write(tasks, fw);
        }
    }

//    public static void readText(TaskList tasks, File file) throws IOException {
//        try (FileReader fr = new FileReader(file)) {
//            read(tasks, fr);
//        }
//
//    }

}
