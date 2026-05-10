package com.doug.socialbooknetwork.payload.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record BookRequest(
      Integer id,
      @NotNull
      @NotEmpty
      String title,
      @NotNull
      @NotEmpty
      String authorName,
      @NotNull
      @NotEmpty
      String isbn,
      @NotNull
      @NotEmpty
      String synopsis,
      boolean shareable
) {
}
