import { useState } from "react";
import { type ErrorResponse, type PasswordConfig, defaultPasswordConfig } from "../types/password";
import { generatePassword } from "../api/passwordApi";

export function usePasswordGenerator() {
    const [config, setConfig] = useState<PasswordConfig>(defaultPasswordConfig)
    const [generatedPassword, setGeneratedPassword] = useState<string>("");
    const [error, setError] = useState<ErrorResponse | null>(null);
    const [isLoading, setIsLoading] = useState(false);
    const [copied, setCopied] = useState(false);

    const generate = async () => {
        setIsLoading(true);
        setError(null);
        setCopied(false);
        setGeneratedPassword("");

        const result = await generatePassword(config)
        
        if (result.password) {
            setGeneratedPassword(result.password);
        } else if (result.error) {
            setError(result.error);
        }

        setIsLoading(false);
    }

    const toggleClass = (key: keyof PasswordConfig) => {
        setConfig(prev => ({ ...prev, [key]: !prev[key] }))
    }

    const updateLength = (length: number) => {
        setConfig(prev => ({ ...prev, length }))
    }

    const copy = () => {
        navigator.clipboard.writeText(generatedPassword).then(() => {
            setCopied(true);
            setTimeout(() => setCopied(false), 2000);
        })
    }

    return {
        config,
        generatedPassword,
        error,
        isLoading,
        copied,
        generate,
        toggleClass,
        updateLength,
        copy
    }
}