package com.comercios.cuentaComerciosBO.constants;

public class ErrorMsg {
    public static final String NO_SUCURSAL = "no corresponde a sucursal de radicacion";
    public static final String UNAUTORIZED = "no autorizado";
    public static final String FILE_NO_CONTENT = "archivo sin contenido";
    //COMERCIO
    public static final String TRADE_NOT_FOUND = "el comercio no se encuentra";

    //DOCUMENTOS
    public static final String DOCUMENT_NOT_FOUND = "no se encuentra el documento";

    //PERMISOS
    public static final String PERMISSION_NOT_FOUND = "permiso no encontrado";
    public static final String PERMISSION_EXISTS = "el permiso ya existe";

    //ROLES
    public static final String ROL_NOT_FOUND = "rol no encontrado";
    public static final String ROL_EXISTS = "el rol ya existe";

    //SUCURSALES
    public static final String BRANCH_CODE_EXISTS = "el codigo de sucursal ya esta registrado";
    public static final String BRANCH_NOT_FOUND = "la sucursal de radicacion no se encuentra";

    //TERMINOS Y CONDICIONES
    public static final String TYC_NOT_FOUND = "terminos y condiciones no se encuentra";

    //USUARIO
    public static final String USER_NOT_FOUND = "el usuario no se encuentra";
    public static final String USER_DNI_EXISTS = "el DNI ya esta registrado";
    public static final String USER_MAIL_EXISTS = "el email ya esta registrado";
    public static final String USER_USERNAME_EXISTS = "el nombre de usuario ya esta registrado";
    //PAGINADO
    public static final String PAGE_GREATER_ZERO = "la pagina debe ser mayor que 0";
}
