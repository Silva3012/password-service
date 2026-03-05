interface Props {
    password: string 
    copied: boolean;
    onCopy: () => void;
}

export default function PasswordResult({ password, copied, onCopy }: Props) {
    if (!password) return null

    return (
    <div className="rounded-lg bg-zinc-900 border border-zinc-800 p-4 space-y-3">
      <p className="font-mono text-lg break-all text-white">{password}</p>
      <button
        onClick={onCopy}
        className="text-xs text-emerald-400 hover:text-emerald-300 transition-colors"
      >
        {copied ? "✓ Copied" : "Copy to clipboard"}
      </button>
    </div>
  )
}