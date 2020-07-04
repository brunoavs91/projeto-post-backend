package com.bruno.post.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bruno.post.exception.FileException;
import com.bruno.post.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

//	public BufferedImage getJpgImageFromFile(MultipartFile uploadFile) {
//		// pegar a extensao do arquivo
//		String extensao = FilenameUtils.getExtension(uploadFile.getOriginalFilename());
//		
//
//		try {
//			BufferedImage img = ImageIO.read(uploadFile.getInputStream());
//			
//			
//			return img;
//		} catch (IOException e) {
//			throw new FileException("Error ao ler arquivo");
//		}
//
//	}
@Override
public byte[] converterArquivoUpload(MultipartFile uploadFile) {
	try {

		byte[] byteArr = uploadFile.getBytes();
		InputStream inputStream = new ByteArrayInputStream(byteArr);
		inputStream.close();
		return byteArr;
	} catch (IOException e) {
		throw new FileException("Error ao ler arquivo");

	}
}
	
	public Pair<InputStream, byte[]> getInputStream(BufferedImage img, String extensao) {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(img, extensao, os);
			return Pair.of(new ByteArrayInputStream(os.toByteArray()), os.toByteArray());
		} catch (IOException e) {
			throw new FileException("Erro ao ler arquivo");
		}

	}
	
	public BufferedImage cropSquare(BufferedImage sourceImg) {
		int min = (sourceImg.getHeight() <= sourceImg.getWidth() ? sourceImg.getHeight() : sourceImg.getWidth());
		return Scalr.crop(sourceImg, (sourceImg.getWidth() / 2 - (min / 2)), (sourceImg.getHeight() / 2 - (min / 2)),
				min, min);
	}
	
	public BufferedImage resize(BufferedImage sourceImg, int size) {
		return Scalr.resize(sourceImg, Scalr.Method.ULTRA_QUALITY, size);
	}
}
