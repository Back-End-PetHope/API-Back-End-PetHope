package com.github.znoque.pethope.mapper;

import com.github.znoque.pethope.dto.user.GlobalUserResponseDto;
import com.github.znoque.pethope.dto.user.UserRequestDto;
import com.github.znoque.pethope.dto.user.UserResponseDto;
import com.github.znoque.pethope.dto.user.clinica.ClinicaRequestDto;
import com.github.znoque.pethope.dto.user.ong.OngRequestDto;
import com.github.znoque.pethope.enums.UsuarioTipo;
import com.github.znoque.pethope.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    public UserResponseDto toUserResponseDto(User user) {

        List<String> authorities = user.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority).toList();
        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getTipo(),
                user.getCpfCnpj(),
                user.getResponsavelNome(),
                user.getTelefone(),
                user.getLogradouro(),
                user.getCidade(),
                user.getPrestadorServico(),
                authorities);
    }

    public GlobalUserResponseDto toGlobalUserResponseDto(User user) {
        List<String> authorities = user.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority).toList();
        return new GlobalUserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getTipo(),
                user.getCpfCnpj(),
                user.getRazaoSocial(),
                user.getResponsavelNome(),
                user.getTelefone(),
                user.getLogradouro(),
                user.getCidade(),
                user.getSite(),
                user.getUrlInstagram(),
                user.getUrlFacebook(),
                user.getPrestadorServico(),
                authorities);
    }

    public User toUser(UserRequestDto userRequestDto, String senha) {
        return User.builder()
                .comCpfCnpj(userRequestDto.cpf())
                .comResponsavelNome(userRequestDto.responsavelNome())
                .comTelefone(userRequestDto.telefone())
                .comCidade(userRequestDto.cidade())
                .comLogradouro(userRequestDto.endereco())
                .comUsername(userRequestDto.email())
                .comSenha(senha)
                .doTipo(UsuarioTipo.USUARIO)
                .build();
    }

    public User toUserOng(OngRequestDto ongRequestDto, String senha) {
        return User.builder()
                .comCpfCnpj(ongRequestDto.cnpj())
                .comResponsavelNome(ongRequestDto.responsavelNome())
                .comTelefone(ongRequestDto.telefone())
                .comCidade(ongRequestDto.cidade())
                .comLogradouro(ongRequestDto.endereco())
                .comRazaoSocial(ongRequestDto.razaoSocial())
                .comUsername(ongRequestDto.email())
                .comSenha(senha)
                .comSite(ongRequestDto.site())
                .comUrlFacebook(ongRequestDto.urlFacebook())
                .comUrlInstagram(ongRequestDto.urlInstagram())
                .doTipo(UsuarioTipo.ONG)
                .doTipoPrestadorServico(ongRequestDto.isPrestadorServico())
                .build();
    }

    public User toUserClinica(ClinicaRequestDto clinicaRequestDto, String senha) {
        return User.builder()
                .comCpfCnpj(clinicaRequestDto.cnpj())
                .comResponsavelNome(clinicaRequestDto.responsavelNome())
                .comTelefone(clinicaRequestDto.telefone())
                .comCidade(clinicaRequestDto.cidade())
                .comLogradouro(clinicaRequestDto.endereco())
                .comRazaoSocial(clinicaRequestDto.razaoSocial())
                .comUsername(clinicaRequestDto.email())
                .comSenha(senha)
                .comSite(clinicaRequestDto.site())
                .comUrlFacebook(clinicaRequestDto.urlFacebook())
                .comUrlInstagram(clinicaRequestDto.urlInstagram())
                .doTipo(UsuarioTipo.CLINICA)
                .doTipoPrestadorServico(clinicaRequestDto.isPrestadorServico())
                .build();
    }
}
