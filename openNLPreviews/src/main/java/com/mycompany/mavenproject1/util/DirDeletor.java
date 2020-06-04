/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.util;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;


public class DirDeletor 
{
    public boolean flushDB(String directoryFilePath)
    {
        
        boolean flag=false;
        try
        {
            
   Path directory = Paths.get(directoryFilePath);

    if (Files.exists(directory))
    {
        Files.walkFileTree(directory, new SimpleFileVisitor<Path>()
        {
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException
            {
                Files.delete(path);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path directory, IOException ioException) throws IOException
            {
                Files.delete(directory);
                return FileVisitResult.CONTINUE;
            }
        });
    }

            
            flag=true;
        }
        
        catch(Exception ex)
        {
            
        }
        
        return flag;
    }
}
