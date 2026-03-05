interface Props {
    value: number;
    onChange: (value: number) => void;
}

export default function LengthSlider({ value, onChange }: Props) {
    return (
        <div className="space-y-2">
            <label htmlFor="length" className="block text-sm font-medium text-gray-700">
                Length: {value}
            </label>
            <input
                id="length"
                type="range"
                min="4"
                max="128"
                value={value}
                onChange={(e) => onChange(parseInt(e.target.value, 10))}
                className="w-full h-2 bg-gray-200 rounded-lg appearance-none cursor-pointer"
            />
        </div>
    );
}