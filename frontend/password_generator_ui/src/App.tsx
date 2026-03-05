
import './App.css'
import CharClassToggle from './components/PasswordGenerator/CharClassToggle'
import ErrorDisplay from './components/PasswordGenerator/ErrorDisplay'
import GenerateButton from './components/PasswordGenerator/GenerateButton'
import LengthSlider from './components/PasswordGenerator/LengthSlider'
import PasswordResult from './components/PasswordGenerator/PasswordResult'
import { usePasswordGenerator } from './hooks/usePasswordGenerator'
import { characterClasses } from './types/password'

function App() {
  const { config, generatedPassword, error, copied, copy,  updateLength, toggleClass, isLoading, generate } =
    usePasswordGenerator()
  return (
    <>
    
    <div className="min-h-screen bg-zinc-500 text-zinc-100 flex items-center justify-center px-4">
      <div className="w-full max-w-md space-y-8">

        <div>
          <h1 className="text-3xl font-bold tracking-tight">Kurz Password Generator</h1>
        </div>

        <LengthSlider value={config.length} onChange={updateLength} />

        <div className="space-y-3">
          <span className="text-sm text-zinc-400">Character classes</span>
          {characterClasses.map(({ key, label, example }) => (
            <CharClassToggle
              key={key}
              label={label}
              example={example}
              active={config[key]}
              onToggle={() => toggleClass(key)}
            />
          ))}
        </div>

        <GenerateButton loading={isLoading} onClick={generate} />
        <PasswordResult password={generatedPassword} copied={copied} onCopy={copy} />
        <ErrorDisplay details={error || null} />

      </div>
    </div>
    </>
  )
}

export default App
