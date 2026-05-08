import { MoonStarIcon, SunMediumIcon } from 'lucide-react'

import { buttonVariants } from '@/components/ui/button'
import { useTheme } from '@/lib/theme'

function ThemeToggle() {
  const { theme, toggleTheme } = useTheme()

  return (
    <button
      type="button"
      onClick={toggleTheme}
      className={buttonVariants({ variant: 'outline', size: 'icon' })}
      aria-label={theme === 'dark' ? 'Activar modo claro' : 'Activar modo oscuro'}
      aria-pressed={theme === 'dark'}
      title={theme === 'dark' ? 'Cambiar a modo claro' : 'Cambiar a modo oscuro'}
    >
      {theme === 'dark' ? (
        <SunMediumIcon className="size-4" />
      ) : (
        <MoonStarIcon className="size-4" />
      )}
    </button>
  )
}

export default ThemeToggle