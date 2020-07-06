package com.bruno.post.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.bruno.post.exception.FileException;
import com.bruno.post.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

	public static  BufferedImage  getJpgImageFromFile(MultipartFile uploadFile) {
		// pegar a extensao do arquivo
//		String extensao = FilenameUtils.getExtension(uploadFile.getOriginalFilename());

		try {
			BufferedImage img = ImageIO.read(uploadFile.getInputStream());
			
			return img;
		} catch (IOException e) {
			throw new FileException("Error ao ler arquivo");
		}

	}
	
	
	public static byte[] getInputStream(BufferedImage img, String extensao) {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(img, extensao, os);
			return os.toByteArray();
		} catch (IOException e) {
			throw new FileException("Erro ao ler arquivo");
		}
		
	}
	public byte[] converterArquivoUpload(MultipartFile uploadFile) {
		try {
	
			byte[] byteArr = uploadFile.getBytes();
			InputStream inputStream = new ByteArrayInputStream(byteArr);
			inputStream.read(byteArr);
			inputStream.close();
			return byteArr;
		} catch (IOException e) {
			throw new FileException("Error ao ler arquivo");
	
		}
	}

	
	public static String convertImagemBytesToString(byte... bytes) {

		if (bytes != null) {

			byte[] imagem = Base64.getUrlDecoder().decode(bytes);

			return new String(bytes);
		}
		return null;
	}

	public static byte[] convertStringToBytes(String imagem) {

		if (!StringUtils.isEmpty(imagem)) {

			String encodedString = Base64.getEncoder().encodeToString(imagem.getBytes());

			return encodedString.getBytes();
		}
		return null;

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
