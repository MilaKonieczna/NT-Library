package com.example.library.controller.book.dto;

public class PatchBookDto {        private int newCopies;

        public PatchBookDto(int newCopies) {
            this.newCopies = newCopies;
        }

        public PatchBookDto() {
        }

        public int getNewCopies() {
            return newCopies;
        }

        public void setNewCopies(int newCopies) {
            this.newCopies = newCopies;
        }
    }

