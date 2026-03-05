interface Props {
    label: string
    example: string;
    active: boolean;
    onToggle: () => void;
}

export default function CharClassToggle({ label, example, active, onToggle }: Props) {
    return (
        <button
            onClick={onToggle}
            className={`flex items-center justify-between w-full px-4 py-2 border rounded-lg transition-colors ${
                active ? "bg-blue-500 text-white border-blue-500" : "bg-white text-gray-700 border-gray-300 hover:bg-gray-100"
            }`}
        >
            <span>{label}</span>
            <span className="text-sm text-gray-400">{example}</span>
        </button>
    );
}