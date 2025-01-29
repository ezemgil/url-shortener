package ezemgil.urlShortener.application;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    private String status;
    private T data;
    private String message;
    private Pagination pagination;

    public Response(String status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public Response(String status, T data, String message, Pagination pagination) {
        this.status = status;
        this.data = data;
        this.message = message;
        this.pagination = pagination;
    }

    public static <T> ResponseEntity<Response<T>> success(T data, String message) {
        return new ResponseEntity<>(new Response<>("success", data, message), HttpStatus.OK);
    }

    public static <T> ResponseEntity<Response<T>> success(T data, String message, Pagination pagination) {
        return new ResponseEntity<>(new Response<>("success", data, message, pagination), HttpStatus.OK);
    }

    public static <T> ResponseEntity<Response<T>> created(T data, String message) {
        return new ResponseEntity<>(new Response<>("success", data, message), HttpStatus.CREATED);
    }

    public static ResponseEntity<Response<Object>> error(String message, HttpStatus status) {
        return new ResponseEntity<>(new Response<>("error", null, message), status);
    }

    public static ResponseEntity<Response<Object>> redirect(String message, HttpStatus status) {
        return new ResponseEntity<>(new Response<>("redirect", null, message), status);
    }

    public static ResponseEntity<Void> noContent() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Getter
    @Setter
    public static class Pagination {
        private int currentPage;
        private int totalPages;
        private long totalItems;

        public Pagination(int currentPage, int totalPages, long totalItems) {
            this.currentPage = currentPage;
            this.totalPages = totalPages;
            this.totalItems = totalItems;
        }

    }
}