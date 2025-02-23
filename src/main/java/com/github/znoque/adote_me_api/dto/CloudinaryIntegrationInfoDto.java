package com.github.znoque.adote_me_api.dto;

/**
 * Representa as informações de integração com o Cloudinary. Esta classe é um
 * tipo imutável (record) que contém os dados necessários para trabalhar com a
 * integração do Cloudinary, incluindo a URL do arquivo e o identificador do
 * arquivo armazenado no serviço.
 *
 * @author Enoque Teixeira Barbosa
 */
public record CloudinaryIntegrationInfoDto(
        /**
         * URL do arquivo armazenado no Cloudinary. Este valor pode ser usado
         * para acessar a imagem ou o vídeo diretamente a partir da plataforma.
         */
        String url,
        /**
         * Identificador único do arquivo no Cloudinary. Esse valor pode ser
         * usado para operações adicionais no Cloudinary, como a exclusão ou
         * atualização do arquivo.
         */
        String fileId) {
    // Não são necessários métodos adicionais ou construtores, pois a classe
    // utiliza a funcionalidade de record do Java, que automaticamente gera
    // os métodos de acesso, construtor e os métodos toString(), equals() e hashCode().

}
