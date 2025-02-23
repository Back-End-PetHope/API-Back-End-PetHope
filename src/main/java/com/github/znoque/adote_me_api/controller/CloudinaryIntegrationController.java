package com.github.znoque.adote_me_api.controller;

import com.github.znoque.adote_me_api.dto.CloudinaryIntegrationInfoDto;
import com.github.znoque.adote_me_api.services.CloudinaryIntegrationService;
import java.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controlador responsável por gerenciar as operações de upload e exclusão de
 * arquivos relacionados à integração com o Cloudinary. Expõe endpoints para que
 * o cliente possa enviar arquivos para o Cloudinary ou removê-los utilizando o
 * seu identificador.
 *
 * O controlador depende do serviço {@link CloudinaryIntegrationService} para
 * realizar as operações reais de upload e exclusão de arquivos.
 *
 * @author Enoque Teixeira Barbosa
 */
@RestController
@RequestMapping("/file-upload")
public class CloudinaryIntegrationController {

    private final CloudinaryIntegrationService cloudinaryIntegrationService;

    /**
     * Construtor que inicializa o controlador com o serviço de integração do
     * Cloudinary.
     *
     * @param cloudinaryIntegrationService O serviço responsável pela integração
     * com o Cloudinary.
     */
    public CloudinaryIntegrationController(CloudinaryIntegrationService cloudinaryIntegrationService) {
        this.cloudinaryIntegrationService = cloudinaryIntegrationService;
    }

    /**
     * Endpoint que recebe um arquivo e faz o upload para o Cloudinary. O
     * arquivo é enviado como parte de um formulário de múltiplos dados
     * (multipart), e o controlador chama o serviço para realizar o upload.
     *
     * @param file O arquivo a ser carregado no Cloudinary.
     * @return Resposta com informações sobre o upload do arquivo (como URL e ID
     * público) ou uma mensagem de erro.
     */
    @PostMapping
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) {
        System.out.println("aaaa");
        try {
            // Chama o serviço para realizar o upload do arquivo
            CloudinaryIntegrationInfoDto info = cloudinaryIntegrationService.uploadFile(file);
            return ResponseEntity.ok(info); // Retorna a URL e o ID do arquivo carregado
        } catch (IllegalArgumentException e) {
            // Retorna uma resposta com status 400 em caso de erro de validação (tipo ou tamanho do arquivo)
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IOException e) {
            // Retorna uma resposta com status 500 em caso de erro interno no servidor ao tentar fazer o upload
            return ResponseEntity.status(500).body("Erro ao fazer upload do arquivo.");
        }
    }

    /**
     * Endpoint que recebe o ID público de um arquivo e o remove do Cloudinary.
     * O controlador chama o serviço para realizar a exclusão do arquivo
     * utilizando o ID fornecido.
     *
     * @param fileId O ID público do arquivo a ser removido.
     * @return Resposta indicando se a remoção foi bem-sucedida ou se houve
     * erro.
     */
    @DeleteMapping
    public ResponseEntity<String> deleteFile(@RequestParam("fileId") String fileId) {
        try {
            // Chama o serviço para excluir o arquivo
            cloudinaryIntegrationService.deleteFile(fileId);
            return ResponseEntity.ok("Arquivo removido com sucesso!"); // Retorna mensagem de sucesso
        } catch (IOException e) {
            // Retorna uma resposta com status 500 em caso de erro ao tentar excluir o arquivo
            return ResponseEntity.status(500).body("Erro ao remover o arquivo.");
        }
    }

}
