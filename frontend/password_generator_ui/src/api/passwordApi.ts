import type { ErrorResponse, PasswordConfig } from "../types/password";


export interface GenerateResult {
    password?: string;
    error?: ErrorResponse;
}

export async function generatePassword(config: PasswordConfig): Promise<GenerateResult> {
    const params = new URLSearchParams({
        length: config.length.toString(),
        useUpperCase: config.useUpperCase.toString(),
        useLowerCase: config.useLowerCase.toString(),
        useNumber: config.useNumber.toString(),
        useSpecialChars: config.useSpecialChars.toString(),
    })

    try {
        const response = await fetch(`/api/generate?${params}`)
        if (response.ok) {
            const password = await response.text();
            return { password };
        } else {
            const error: ErrorResponse = await response.json();
            return { error };
        }
    } catch {
        return {  error: { error: "Network error", details: "Could not reach the server." } };
    }
}