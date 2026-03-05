interface Props {
    loading: boolean;
    onClick: () => void;
}

export default function GenerateButton({ loading, onClick }: Props) {
    return (
        <button
            onClick={onClick}
            disabled={loading}
            className={`w-full px-4 py-2 text-white font-medium rounded-lg transition-colors ${
                loading ? "bg-gray-400 cursor-not-allowed" : "bg-green-500 hover:bg-green-600"
            }`}
        >
            {loading ? "Generating..." : "Generate Password"}
        </button>
    );
}