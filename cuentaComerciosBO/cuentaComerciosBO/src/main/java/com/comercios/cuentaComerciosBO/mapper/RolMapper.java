package com.comercios.cuentaComerciosBO.mapper;

import com.comercios.cuentaComerciosBO.dto.RolDTO;
import com.comercios.cuentaComerciosBO.entity.Rol;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RolMapper {
    RolDTO rolToRolDTO(Rol request);
    List<RolDTO> rolListToRolDTOList(List<Rol> request);

    Rol rolDTOToRol(RolDTO request);
    List<Rol> rolDTOListToRolList(List<RolDTO> request);
}
