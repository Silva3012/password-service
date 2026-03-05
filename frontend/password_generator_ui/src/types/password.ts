export interface PasswordConfig {
    length: number;
    useUpperCase: boolean;
    useLowerCase: boolean;
    useNumber: boolean;
    useSpecialChars: boolean;
}

export interface ErrorResponse {
    error: string;
    details?: string;
}

export const defaultPasswordConfig: PasswordConfig = {
    length: 12,
    useUpperCase: true,
    useLowerCase: true,
    useNumber: true,
    useSpecialChars: false,
}

export const characterClasses = [
    {key: "useUpperCase", label: "Uppercase", example: "A-Z"},
    {key: "useLowerCase", label: "Lowercase", example: "a-z"},
    {key: "useNumber", label: "Numbers", example: "0-9"},
    {key: "useSpecialChars", label: "Special Characters", example: "!@#$%^&*()"},
] as const