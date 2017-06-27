package org.lotuscloud.builder;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Copyright (c) 2017 Lennart Heinrich
 * www.lennarth.com
 */
public class Builder {

    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println("Starte LotusCloud Builder...\nAbbrechen mit STRG + C");
        Thread.sleep(3000);

        ProcessBuilder gitClone = new ProcessBuilder("git", "clone", "https://github.com/NexusByte/LotusCloud.git");
        Process gitCloneProcess = gitClone.start();

        Scanner scanner1 = new Scanner(gitCloneProcess.getInputStream());
        while (gitCloneProcess.isAlive())
            if (scanner1.hasNextLine())
                System.out.println(scanner1.nextLine());

        ProcessBuilder apiInstall = new ProcessBuilder("mvn", "clean", "install");
        apiInstall.directory(new File("LotusCloud/API"));
        Process apiInstallProcess = apiInstall.start();

        Scanner scanner2 = new Scanner(apiInstallProcess.getInputStream());
        while (apiInstallProcess.isAlive())
            if (scanner2.hasNextLine())
                System.out.println(scanner2.nextLine());

        ProcessBuilder masterPackage = new ProcessBuilder("mvn", "clean", "package");
        masterPackage.directory(new File("LotusCloud/Master"));
        Process masterPackageProcess = masterPackage.start();

        Scanner scanner3 = new Scanner(masterPackageProcess.getInputStream());
        while (masterPackageProcess.isAlive())
            if (scanner3.hasNextLine())
                System.out.println(scanner3.nextLine());

        ProcessBuilder wrapperPackage = new ProcessBuilder("mvn", "clean", "package");
        wrapperPackage.directory(new File("LotusCloud/Wrapper"));
        Process wrapperPackageProcess = wrapperPackage.start();

        Scanner scanner4 = new Scanner(wrapperPackageProcess.getInputStream());
        while (wrapperPackageProcess.isAlive())
            if (scanner4.hasNextLine())
                System.out.println(scanner4.nextLine());

        File file = new File("");
        System.out.println("\n\n\n\n\n\n\n\n\n\nMaster Jar: " + file.getAbsolutePath() + "/LotusCloud/Master/target\nWrapper Jar: " + file.getAbsolutePath() + "/LotusCloud/Wrapper/target");
        System.out.println("\nFertig!");
        Thread.sleep(1000);
    }
}