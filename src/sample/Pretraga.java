package sample;

import javafx.collections.ObservableList;

import java.io.File;

public class Pretraga implements Runnable {
    private File file;
    private Controller controller;
    private String podstring = "";
    private ObservableList<File> list;

    @Override
    public void run() {
        for (int i=1; i<=100; i++)
            System.out.print(" " + i);

        try {
            trazi(file);
        } catch (Exception e) {
            System.out.println("Izuzetak " + e.getMessage());
        }
    }


    public Pretraga (Controller controller) {
        file = new File (System.getProperty("user.home"));
        this.controller = controller;
        podstring = podstring + controller.onFind.getText().trim();
        list = controller.fileList();
    }

    public void trazi (File file) throws Exception {
        if (file.isFile()) {
            if (file.getName().contains(podstring)) {
                if (list != null) {
                    list.add(file);
                }
            }
        } else {
            try {
                for (File f : file.listFiles()) {
                    trazi(f);
                }
            } catch (NullPointerException e) {
                System.out.println("Izuzetak " + e.getMessage());
            }
        }
    }
}