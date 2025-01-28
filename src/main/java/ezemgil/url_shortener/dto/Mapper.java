package ezemgil.url_shortener.dto;

public interface Mapper<T, U> {
    T toDTO(U entity);
    U fromDTO(T dto);
}
