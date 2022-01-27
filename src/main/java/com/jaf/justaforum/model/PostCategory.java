package com.jaf.justaforum.model;

//model odzwierciedlający kategorie postów
public enum PostCategory {
    FRONTEND {
        @Override
        public String toString() {
            return "FRONTEND";
        }
    },
    BACKEND {
        @Override
        public String toString() {
            return "BACKEND";
        }
    },
    MOBILE {
        @Override
        public String toString() {
            return "MOBILE";
        }
    }
}
