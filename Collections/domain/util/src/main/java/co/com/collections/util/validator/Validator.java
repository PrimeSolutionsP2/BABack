package co.com.collections.util.validator;

public interface Validator<T> {
    void validate(T object) throws ValidationException;
}