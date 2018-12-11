package controller;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xslf.usermodel.SlideLayout;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFPictureShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFSlideLayout;
import org.apache.poi.xslf.usermodel.XSLFSlideMaster;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

public class GenerateurPPTX {
	
	public static void main(String args[]) throws IOException{
		//cr�er un nouveau Slide Show vide
		String path= "/Desktop/Unfichier.pptx";
		File Background =new File(System.getProperty("user.home")+"/Desktop/fond_transp.png");
		String NomMedoc="doliprane 500mg";
		File ImgMedoc =new File(System.getProperty("user.home")+"/Desktop/doliprane.png");
		File file =new File(System.getProperty("user.home") + path);
		newFile(file);
		String TextMedoc="Le doliprane c'est pour le mal de t�te tu savais ou pas ? plus pr�cis�ment c'est un anti-douleur, il fait 500mg ca veut dire que c'est plus petit que 1000 par exemple ";
		String TextMedoc2="Le saviez-vous ? le doliprane faut pas en prendre 3 dans la meme minute";
		
		//Dispo1(file,NomMedoc,TextMedoc,TextMedoc2,ImgMedoc,Background);
		//Dispo2(file,NomMedoc,TextMedoc,TextMedoc2,ImgMedoc,Background);
		//Dispo3(file,NomMedoc,ImgMedoc,Background);
		Dispo4(file,NomMedoc,TextMedoc,TextMedoc2,Background);
		
		
		   }
	
	public static void newFile(File file) throws IOException {
		 //cr�er un objet FileOutputStream pour enregistrer le document PPT
		 XMLSlideShow xml = new XMLSlideShow();
	     FileOutputStream fis = new FileOutputStream(file);
	     //sauvegarder le fichier
	     xml.write(fis);

	     
	     System.out.println(file);
	     System.out.println("Fichier enregistr�");
	     fis.close();
	     xml.close();
	      
	      }
	 public static void newSlide(File fichier,File ImgBackground,Integer numSlide) throws IOException {
		 FileInputStream inputstream=new FileInputStream(fichier);
		 XMLSlideShow xml = new XMLSlideShow(inputstream);
		 XSLFSlideMaster slideMaster = xml.getSlideMasters().get(0);
		 XSLFSlideLayout TitleEtContent = slideMaster.getLayout(SlideLayout.TITLE_AND_CONTENT);
		 xml.createSlide(TitleEtContent);
		 
		 
		 FileOutputStream fos = new FileOutputStream(fichier);
	      
	      xml.write(fos);
	      
	      newSlideImg(fichier,ImgBackground,"Background",numSlide);
	      
	      fos.close(); 
	      xml.close();
	 }
	 
	 public static void modifSlide(File fichier,String title,String paragraphe,Integer numSlide,String position) throws IOException{
	      FileInputStream inputstream=new FileInputStream(fichier);
	      XMLSlideShow xml = new XMLSlideShow(inputstream);
	      
	      List<XSLFSlide> slides = xml.getSlides();
	      XSLFSlide slide = (XSLFSlide) slides.get(numSlide);
	      
	      XSLFTextShape zoneTitre = slide.getPlaceholder(0);
	      XSLFTextShape zoneText =slide.getPlaceholder(1);
	      zoneTitre.clearText();
	      zoneText.clearText();
	      
	      XSLFTextParagraph leTitre=zoneTitre.addNewTextParagraph();
	      XSLFTextRun run1 = leTitre.addNewTextRun();
	      
	      XSLFTextParagraph leText=zoneText.addNewTextParagraph();
	      XSLFTextRun run2 = leText.addNewTextRun();
	      
          run1.setText(title);
          run2.setText(paragraphe);
          
          if(position=="gauche") {
        	  zoneText.setAnchor(new Rectangle(0,150,350,150));
          }
          if(position=="droite") {
        	  zoneText.setAnchor(new Rectangle(400,150,350,150));
          }
          if(position=="centre") {
        	  zoneText.setAnchor(new Rectangle(150,150,500,200));
          }
          
        	  //Gauche zoneText.setAnchor(new Rectangle(0,150,350,150));
        	  //Droite zoneText.setAnchor(new Rectangle(400,150,350,150));  

          run2.setFontSize((double) 24); 
	      
	      
	    
	      //ajouter des slides � la pr�sentation
	      FileOutputStream fis = new FileOutputStream(fichier);
	      xml.write(fis);
	      System.out.println("texte ajout�");
	      fis.close();
	      xml.close();
	   }
	 
	 
	 public static void newSlideImg(File fichier,File img, String position,Integer numSlide) throws IOException{
		 FileInputStream inputstream=new FileInputStream(fichier);
		 XMLSlideShow xml = new XMLSlideShow(inputstream);
		 List<XSLFSlide> slides = xml.getSlides();
	      XSLFSlide slide = (XSLFSlide) slides.get(numSlide);

		 byte[] picture = IOUtils.toByteArray(new FileInputStream(img));
	      
	      //ajouter l'image � la pr�sentation
	      PictureData picdata = xml.addPicture(picture, PictureData.PictureType.PNG);
	      
	      
	      //cr�er un slide pour cette image ajout�e
	     XSLFPictureShape pic = slide.createPicture(picdata);
	     
	     // on prend notre image pour r�cup�rer ses dimentions, car getWidth n'existe pas, on transforme les dimentions en string
	     String stringAnchor= pic.getAnchor().toString();
	     
	     //Permet de r�cup�rer la largeur de l'image, via les dimentions transformer en string
	     int widthImg =Integer.parseInt(stringAnchor.substring(stringAnchor.indexOf('w',7)+2,stringAnchor.indexOf('.',stringAnchor.indexOf('w',7))));
	     int heightImg=Integer.parseInt(stringAnchor.substring(stringAnchor.indexOf('h')+2,stringAnchor.indexOf('.',stringAnchor.indexOf('h'))));


	     //System.out.println(xml.getPageSize());
	     
	     int widthSlide=(int) xml.getPageSize().getWidth();
	     int heightSlide=(int) xml.getPageSize().getHeight();
	     if(position=="centre") {
	     // Pour le centre il faut diviser width et height de la slide par 2 moins la width et la height de l'image
	     pic.setAnchor(new Rectangle((widthSlide-widthImg)/2,(heightSlide-heightImg)/2,widthImg,heightImg));
	     System.out.println("Image ajout�e et centr�e !");
	     
	     }
	     if(position=="gauche") {
	    	 pic.setAnchor(new Rectangle(10,(heightSlide-heightImg)/2,widthImg,heightImg));
		     System.out.println("Image ajout�e et position�e � gauche !");
		  
	     }
	     if(position=="droite") {
	    	 pic.setAnchor(new Rectangle(widthSlide-widthImg-10,(heightSlide-heightImg)/2,widthImg,heightImg));
		     System.out.println("Image ajout�e et position�e � droite !");

	     }
	     if(position=="Background") {
	    	 pic.setAnchor(new Rectangle(0,0,widthSlide,heightSlide));
	    	
	     }
	      FileOutputStream fos = new FileOutputStream(fichier);
	      
	      
	      xml.write(fos);
	      
	      fos.close(); 
	      xml.close();
	 }
	 
	 public static void Dispo1(File file,String NomMedoc,String TextMedoc,String TextMedoc2,File imgMedoc,File imgBackground) throws IOException{
		
		 
		 for (int i = 0; i < 4; i++) {
				newSlide(file,imgBackground,i);
			}
		 
		 //slide 1
			modifSlide(file,NomMedoc,"",0,"gauche");
			//slide 2
			modifSlide(file,NomMedoc,TextMedoc,1,"gauche");
			newSlideImg(file,imgMedoc,"droite",1);
			//slide 3
			modifSlide(file,NomMedoc,TextMedoc2,2,"gauche");
			newSlideImg(file,imgMedoc,"droite",2);
			//slide 4
			modifSlide(file,"\n\n\nC'�tait la pr�sentation de "+NomMedoc+" merci pour votre attention !","",3,"gauche");
			
		 
	 }
	 
	 public static void Dispo2(File file,String NomMedoc,String TextMedoc,String TextMedoc2,File imgMedoc,File imgBackground) throws IOException{
			
		 
		 for (int i = 0; i < 4; i++) {
				newSlide(file,imgBackground,i);
			}
		 
		//slide 1
		modifSlide(file,NomMedoc,"",0,"droite");
		//slide 2
		modifSlide(file,NomMedoc,TextMedoc,1,"droite");
		newSlideImg(file,imgMedoc,"gauche",1);
		//slide 3
		modifSlide(file,NomMedoc,TextMedoc2,2,"droite");
		newSlideImg(file,imgMedoc,"gauche",2);
		//slide 4
		modifSlide(file,"\n\n\nC'�tait la pr�sentation de "+NomMedoc+" merci pour votre attention !","",3,"droite");
				
			 
		 }
	 public static void Dispo3(File file,String NomMedoc,File imgMedoc,File imgBackground) throws IOException{
		 for (int i = 0; i < 3; i++) {
			 newSlide(file,imgBackground,i);
			 }
		 // slide 1
		 modifSlide(file,NomMedoc,"",0,"droite");
		 //slide 2
		 modifSlide(file,NomMedoc,"",1,"droite");
		 newSlideImg(file,imgMedoc,"centre",1);
		 //slide 3
		 modifSlide(file,"\n\n\nC'�tait la pr�sentation de "+NomMedoc+" merci pour votre attention !","",2,"droite");
		 
	 }
	 public static void Dispo4(File file,String NomMedoc,String TextMedoc,String TextMedoc2,File imgBackground) throws IOException{
		 for (int i = 0; i < 4; i++) {
				newSlide(file,imgBackground,i);
			}
		 
		//slide 1
		modifSlide(file,NomMedoc,"",0,"centre");
		//slide 2
		modifSlide(file,NomMedoc,TextMedoc,1,"centre");
		//slide 3
		modifSlide(file,NomMedoc,TextMedoc2,2,"centre");
		//slide 4
		modifSlide(file,"\n\n\nC'�tait la pr�sentation de "+NomMedoc+" merci pour votre attention !","",3,"centre");
		 
	 }
			
	 }


