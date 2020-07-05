package com.bruno.post.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

	/**
	 * Converte o arquivo recebido em bytes para ser armazenado no banco
	 * @param uploadFile
	 * @return
	 */
	public byte[] converterArquivoUpload(MultipartFile uploadFile);
	
//	/**
//	 * converter bytes para string
//	 * @param bytes
//	 * @return
//	 */
//	public String convertImagemBytesToString(byte... bytes);
//	
//	/**
//	 * Converter string para Bytes
//	 * @param imagem
//	 * @return
//	 */
//	public byte[] convertStringToBytes(String imagem);
}
