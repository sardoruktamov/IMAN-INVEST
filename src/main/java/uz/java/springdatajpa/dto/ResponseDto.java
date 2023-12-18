package uz.java.springdatajpa.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> {

    /**
     * Agar so'rov muvaffaqiyatli amalga oshirilgan bo'lsa, true, ask holda false
     */
    private boolean success;
    /**
     * So'rov bo'yicha qo'shimcha ma'lumot yuborish uchun xabar
     */
    private String message;
    /**
     * 0 - hammasi yaxshi(default)
     * 1 - validation error
     * 2 - database error
     * -1 - not found
     * 2 - unexpected error
     */
    private int code;
    /**
     * So'rovga yuborilgan javob tanasi
     */
    private T data;
    private List<ErrorDto> errors;
}
