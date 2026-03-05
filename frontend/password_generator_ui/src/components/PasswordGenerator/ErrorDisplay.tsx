import type { ErrorResponse } from "../../types/password"

interface Props {
  details: ErrorResponse | null
}

export default function ErrorDisplay({ details }: Props) {
  if (!details) return null

  return (
    <div className="rounded-lg bg-red-950 border border-red-800 p-4 space-y-1">
      <p className="text-xs text-red-500">{details.details}</p>
      <p className="text-sm font-semibold text-red-400">{details.error}</p>
    </div>
  )
}