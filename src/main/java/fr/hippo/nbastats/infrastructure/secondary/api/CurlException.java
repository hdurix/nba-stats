package fr.hippo.nbastats.infrastructure.secondary.api;

class CurlException extends RuntimeException {

    CurlException(String message) {
        super(message);
    }

    CurlException(String message, Throwable cause) {
        super(message, cause);
    }
}
