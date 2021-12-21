/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.circles;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.imageio.ImageIO;

/**
 *
 * @author russi
 */
public class FindCircles{
    public static void main (String[] args) throws IOException{
        File file = new File("C:\\Users\\russi\\Documents\\NetBeansProjects\\Circles\\src\\main\\java\\com\\mycompany\\circles\\miniMmoon.jpg");
        BufferedImage source = ImageIO.read(file);
        
        BufferedImage result = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        
        //делаем черно-белый рисунок
            for (int x = 0; x < source.getWidth(); x++) {
                for (int y = 0; y < source.getHeight(); y++) {
                    Color color = new Color(source.getRGB(x,y));
                    int grey = (int)(0.33*color.getBlue()+0.33*color.getGreen()+0.33*color.getRed());
                    Color newColor = new Color(grey, grey, grey);
                    result.setRGB(x, y, newColor.getRGB());
                }
            }    
            File output = new File("C:\\Users\\russi\\Documents\\NetBeansProjects\\Circles\\src\\main\\java\\com\\mycompany\\circles\\football_grey.jpg");
            ImageIO.write(result, "jpg", output);
            BufferedImage greySource = ImageIO.read(output);
            
            BufferedImage res =  new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
            
            //сглаживаем рисунок
            for (int x = 0; x < greySource.getWidth(); x++) {
                for (int y = 0; y < greySource.getHeight(); y++) {
                    
                    ArrayList<Integer> myArrR=new ArrayList<>();
                    
                    Color color2 = new Color(greySource.getRGB(x, y));
                    myArrR.add(color2.getRed());
                   
                    int win = 13;
                    for (int i=1; i<=((win-1)/2);i++){
                        if (x+i<=greySource.getWidth()-1){
                            color2 = new Color(greySource.getRGB(x+i, y));
                            myArrR.add(color2.getRed());
                        }
                        if (x-i>=0){
                            color2 = new Color(greySource.getRGB(x-i, y));
                            myArrR.add(color2.getRed());
                        }
                        if (y+i<=greySource.getHeight()-1){
                            color2 = new Color(greySource.getRGB(x, y+i));
                            myArrR.add(color2.getRed());
                        }
                        if (y-i>=0){
                            color2 = new Color(greySource.getRGB(x, y-i));
                            myArrR.add(color2.getRed());
                        }
                        if (x+i<=greySource.getWidth()-1&&y-i>=0){
                            color2 = new Color(greySource.getRGB(x+i, y-i));
                            myArrR.add(color2.getRed());
                        }
                        if (x+i<=greySource.getWidth()-1&&y+i<=greySource.getHeight()-1){
                            color2 = new Color(greySource.getRGB(x+i, y+i));
                            myArrR.add(color2.getRed());
                        }
                        if (x-i>=0&&y-i>=0){
                            color2 = new Color(greySource.getRGB(x-i, y-i));
                            myArrR.add(color2.getRed());
                        }
                        if (x-i>=0&&y+i<=greySource.getHeight()-1){
                            color2 = new Color(greySource.getRGB(x-i, y+i));
                            myArrR.add(color2.getRed());
                        }
                    }
                    
                    int mediane = myArrR.size()/2;
                    int newR=0;
                    Collections.sort(myArrR);
                    if (myArrR.get(mediane)>0){
                        newR = myArrR.get(mediane)-1;
                    }else{
                        newR = myArrR.get(mediane);
                    }
                    
                    
                    Color col = new Color(newR, newR, newR);
                    res.setRGB(x,y,col.getRGB());
                }
             }    
            
            File output2 = new File("C:\\Users\\russi\\Documents\\NetBeansProjects\\Circles\\src\\main\\java\\com\\mycompany\\circles\\football_slow.jpg");
            ImageIO.write(res, "jpg", output2);
            
            //границы
            BufferedImage res1 =  new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
            
            BufferedImage newImage = ImageIO.read(output2);
            Color ccolor = new Color(newImage.getRGB(0,0));
            int cgrey = ccolor.getRed();
            res1.setRGB(0, 0, 0);
            int k=10;
            for (int x = 0; x < newImage.getWidth(); x++) {
                for (int y = 0; y < newImage.getHeight(); y++) {
                    Color color = new Color(newImage.getRGB(x,y));
                    int grey = color.getRed();
                    if (x+1<newImage.getWidth()){
                        Color colors = new Color(newImage.getRGB(x+1,y));
                        int greys = colors.getRed();
                        if (Math.abs(grey-greys)>=k){
                            Color c = new Color(255,255,255);
                            res1.setRGB(x+1, y, c.getRGB());
                        }else{
                            Color c = new Color(0,0,0);
                            res1.setRGB(x+1, y, c.getRGB());
                        }
                    }
                    if (y+1<newImage.getHeight()){
                        Color colors = new Color(newImage.getRGB(x,y+1));
                        int greys = colors.getRed();
                        if (Math.abs(grey-greys)>=k){
                            Color c = new Color(255,255,255);
                            res1.setRGB(x, y+1, c.getRGB());
                        }else{
                            Color c = new Color(0,0,0);
                            res1.setRGB(x, y+1, c.getRGB());
                        }
                    }
                    if (x+1<newImage.getWidth()&&y+1<newImage.getHeight()){
                        Color colors = new Color(newImage.getRGB(x+1,y+1));
                        int greys = colors.getRed();
                        if (Math.abs(grey-greys)>=k){
                            Color c = new Color(255,255,255);
                            res1.setRGB(x+1, y+1, c.getRGB());
                        }else{
                            Color c = new Color(0,0,0);
                            res1.setRGB(x+1, y+1, c.getRGB());
                        }
                    }
                }
            }
            File output3 = new File("C:\\Users\\russi\\Documents\\NetBeansProjects\\Circles\\src\\main\\java\\com\\mycompany\\circles\\football_lines.jpg");
            ImageIO.write(res1, "jpg", output3);
            
            BufferedImage mImg = ImageIO.read(output3);
            
           int maxR=75, minX=0, maxX=mImg.getWidth()-0, minY=0, maxY=mImg.getHeight()-0, R=0;
           int[][][] myArr = new int[maxX-minX+2][maxY-minY+2][maxR+1]; 
           for (int x=0; x<mImg.getWidth(); x++){
               for (int y=0; y<mImg.getHeight(); y++){
                   Color C = new Color(mImg.getRGB(x, y));
                   if (C.getBlue()>200){
                       for (int a=minX; a<=maxX; a++){
                           for (int b=minY; b<maxY; b++){
                               R=(int)(Math.sqrt(Math.pow(x-a,2)+Math.pow(y-b,2)));
                               if (R<=maxR){
                                myArr[a-minX][b-minY][R]++;
                               }
                           }
                       }
                   }
               }
           }
            int mmax=0; int[]ki=new int[100]; int[]kj=new int[100]; int[]kz=new int[100];
            int krugi=0;
            for (int i=0; i<maxX-minX-10; i++){
               for (int j=0; j<maxY-minY; j++){
                   for (int z=0; z<maxR; z++){
                       if (myArr[i][j][z]>95){
                           ki[krugi]=i;
                           kj[krugi]=j;
                           kz[krugi]=z;
                           krugi++;
                           j=0;
                           i+=10;
                           z=0;
                       }
                   }
               }
           }
            System.out.println(krugi);
           
           Color paint = new Color(255,10,10);
           File output4 = new File("C:\\Users\\russi\\Documents\\NetBeansProjects\\Circles\\src\\main\\java\\com\\mycompany\\circles\\football_lines_1.jpg");
           BufferedImage ress1 = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
           for (int i=0; i<source.getWidth(); i++){
               for (int j=0; j<source.getHeight(); j++){
                   Color clr = new Color(source.getRGB(i, j));
                   for (int u=0; u<=krugi; u++){
                   if (i==ki[u]&&j==kj[u]){
                            ress1.setRGB(i, j, paint.getRGB());
                        }else{
                            ress1.setRGB(i, j, clr.getRGB());
                        }
                   }
               }
           }
           for (int x=0; x<source.getWidth(); x++){
               for (int y=0; y<source.getHeight(); y++){
                   for (int u=0; u<krugi; u++){
                       if (kz[u]==(int)(Math.sqrt(Math.pow(x-ki[u],2)+Math.pow(y-kj[u],2)))){
                           ress1.setRGB(x, y, paint.getRGB());
                       }
                   }            
                }
           }
           
            
            
           ImageIO.write(ress1, "jpg", output4);
    }
}
