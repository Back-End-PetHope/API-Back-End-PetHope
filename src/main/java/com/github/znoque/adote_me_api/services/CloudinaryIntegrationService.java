package com.github.znoque.adote_me_api.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.github.znoque.adote_me_api.dto.CloudinaryIntegrationInfoDto;
import java.io.IOException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Serviço responsável pela integração com a plataforma Cloudinary. Este serviço
 * oferece métodos para fazer upload e exclusão de arquivos, bem como validação
 * de tipo e tamanho dos arquivos.
 *
 * A integração com o Cloudinary é configurada usando as credenciais
 * (cloud_name, api_key, api_secret) fornecidas via propriedades do Spring.
 *
 * @author Enoque Teixeira Barbosa
 */
@Service
public class CloudinaryIntegrationService {

    private final Cloudinary cloudinary;

    /**
     * Construtor que inicializa a instância do serviço
     * CloudinaryIntegrationService com as credenciais fornecidas via
     * propriedades do Spring.
     *
     * @param cloudName O nome da nuvem do Cloudinary.
     * @param apiKey A chave de API para autenticação com o Cloudinary.
     * @param apiSecret O segredo da chave de API para autenticação com o
     * Cloudinary.
     */
    public CloudinaryIntegrationService(
            @Value("${cloudinary.cloud-name}") String cloudName,
            @Value("${cloudinary.api-key}") String apiKey,
            @Value("${cloudinary.api-secret}") String apiSecret) {

        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret
        ));
    }

    /**
     * Realiza o upload de um arquivo para o Cloudinary. O arquivo será validado
     * quanto ao tipo e tamanho antes do upload. O arquivo será armazenado em
     * uma pasta diferente dependendo se é uma imagem ou vídeo.
     *
     * @param file O arquivo a ser enviado para o Cloudinary.
     * @return Um objeto {@link CloudinaryIntegrationInfoDto} contendo a URL
     * segura e o ID público do arquivo.
     * @throws IOException Se ocorrer um erro ao tentar carregar o arquivo para
     * o Cloudinary.
     * @throws IllegalArgumentException Se o tipo ou tamanho do arquivo for
     * inválido.
     */
    public CloudinaryIntegrationInfoDto uploadFile(MultipartFile file) throws IOException {

        String folder = "";
        String contentType = file.getContentType();

        // Valida se o tipo de arquivo é permitido
        if (!isValidFileType(file)) {
            throw new IllegalArgumentException("Formato de arquivo inválido. Formatos permitidos: JPG, PNG, WebP, MP4, WebM e MOV.");
        }

        // Valida se o tamanho do arquivo é permitido
        if (!isValidFileSize(file)) {
            throw new IllegalArgumentException("O arquivo excede o tamanho permitido.");
        }

        // Define a pasta no Cloudinary dependendo do tipo de arquivo
        if (contentType.startsWith("image/")) {
            folder = "app-pet/usuario/image";
        } else if (contentType.startsWith("video/")) {
            folder = "app-pet/usuario/video";
        }

        // Opções para o upload do arquivo
        Map<String, Object> uploadOptions = ObjectUtils.asMap(
                "folder", folder,
                "resource_type", "auto", // Detecta automaticamente o tipo de recurso (imagem, vídeo, etc.)
                "quality", "auto", // Define qualidade automática
                "bit_rate", "auto", // Define taxa de bits automática
                "timeout", 60000 // Ajusta o timeout, caso o upload demore
        );

        // Realiza o upload do arquivo
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), uploadOptions);

        // Recupera a URL segura e o ID público do arquivo carregado
        String url = uploadResult.get("secure_url").toString();
        String publicId = uploadResult.get("public_id").toString();

        // Retorna um DTO com as informações do upload
        CloudinaryIntegrationInfoDto info = new CloudinaryIntegrationInfoDto(url, publicId);

        return info;
    }

    /**
     * Exclui um arquivo do Cloudinary. O arquivo é removido utilizando o seu
     * identificador público.
     *
     * @param publicId O ID público do arquivo a ser excluído.
     * @throws IOException Se ocorrer um erro ao tentar excluir o arquivo do
     * Cloudinary.
     */
    public void deleteFile(String publicId) throws IOException {
        // Opções para a exclusão, com invalidação do cache
        Map<String, Object> options = ObjectUtils.asMap(
                "invalidate", true
        );

        // Realiza a exclusão do arquivo
        Map result = cloudinary.uploader().destroy(publicId, options);
        System.out.println("Deletado: " + result);
    }

    /**
     * Verifica se o tipo de arquivo é válido, de acordo com os formatos
     * permitidos.
     *
     * @param file O arquivo a ser verificado.
     * @return True se o tipo de arquivo for válido, False caso contrário.
     */
    private boolean isValidFileType(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && (contentType.equals("image/jpeg")
                || contentType.equals("image/png")
                || contentType.equals("image/webp")
                || contentType.equals("video/mov")
                || contentType.equals("video/webm")
                || contentType.equals("video/mp4"));
    }

    /**
     * Verifica se o tamanho do arquivo é válido, de acordo com os limites
     * estabelecidos.
     *
     * @param file O arquivo a ser verificado.
     * @return True se o tamanho do arquivo for válido, False caso contrário.
     */
    private boolean isValidFileSize(MultipartFile file) {
        long maxImageSize = 10 * 1024 * 1024; // 10MB
        long maxVideoSize = 100 * 1024 * 1024; // 100MB

        String contentType = file.getContentType();
        long fileSize = file.getSize();

        if (contentType == null) {
            return false;
        }

        if (contentType.startsWith("image/")) {
            return fileSize <= maxImageSize;
        } else if (contentType.startsWith("video/")) {
            return fileSize <= maxVideoSize;
        }

        return false;
    }
    
}
