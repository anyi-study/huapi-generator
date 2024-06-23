package com.codej.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * 静态文件生成
 */
public class StaticGenerator {

    /**
     * 拷贝文件（Hutool 实现，会将输入目录完整拷贝到输出目录下）
     *
     * @param inputPath  输入目录路径
     * @param outputPath 输出目录路径
     */
    public static void copyFilesByHutool(String inputPath, String outputPath) {
        FileUtil.copy(inputPath, outputPath, false);
    }

    public static void main(String[] args) {
        // 获取整个项目的根路径
        String projectPath = System.getProperty("user.dir");
        File parentFile = new File(projectPath).getParentFile();
        // 输入路径：ACM 示例代码模板目录
        String inputPath = new File(parentFile, "yuzi-generator-demo-projects/acm-template").getAbsolutePath();
        // 输出路径：直接输出到项目的根目录
        String outputPath = projectPath;
        copyFilesByHutool(inputPath, outputPath);
    }

    /**
     * 递归拷贝文件（递归实现，会将输入目录完整拷贝到输出目录下）
     *
     * @param inputPath  输入路径
     * @param outputPath 输出路径
     */
    public static void copyFilesByRecursive(String inputPath, String outputPath) {
        File inputFile = new File(inputPath);
        File outputFile = new File(outputPath);
        try {
            copyFileByRecursive(inputFile, outputFile);
        } catch (Exception e) {
            System.err.println("文件复制失败");
            e.printStackTrace();
        }
    }

    /**
     * 递归辅助方法，用于实际执行文件或目录的拷贝操作
     *
     * @param inputFile  输入文件或目录
     * @param outputFile 输出文件或目录
     * @throws IOException 抛出可能的IO异常
     */
    private static void copyFileByRecursive(File inputFile, File outputFile) throws IOException {
        if (inputFile.isDirectory()) {
            System.out.println(inputFile.getName());
            File destOutputFile = new File(outputFile, inputFile.getName());
            if (!destOutputFile.exists()) {
                destOutputFile.mkdirs();
            }
            File[] files = inputFile.listFiles();
            if (ArrayUtil.isEmpty(files)) {
                return;
            }
            for (File file : files) {
                copyFileByRecursive(file, destOutputFile);
            }
        } else {
            Path destPath = outputFile.toPath().resolve(inputFile.getName());
            Files.copy(inputFile.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
