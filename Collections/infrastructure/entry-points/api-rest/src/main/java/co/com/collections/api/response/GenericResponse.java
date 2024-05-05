package co.com.collections.api.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse<E> {
    private String message;
    private int statusCode;
    private String errorCode;
    private E data;

    public GenericResponse(String message) {
        this.message = message;
    }

    public GenericResponse(String message, int statusCode, String errorCode) {
        this.message = message;
        this.statusCode = statusCode;
        this.errorCode = errorCode;
    }
}
