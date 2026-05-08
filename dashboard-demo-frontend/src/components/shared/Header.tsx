import { BarChart3Icon } from 'lucide-react'
import { Link } from 'react-router'

import { buttonVariants } from '@/components/ui/button'

function HomeHeader() {
  return (
    <header className="mx-auto w-full max-w-6xl pb-8">
      <div className="flex flex-col gap-4 rounded-[2rem] border bg-white/80 px-5 py-4 shadow-sm backdrop-blur sm:flex-row sm:items-center sm:justify-between">
        <Link to="/home" className="flex items-center gap-3">
          <span className="grid size-10 place-items-center rounded-2xl bg-foreground text-background shadow-sm">
            <BarChart3Icon className="size-5" />
          </span>
          <div>
            <p className="text-sm font-semibold uppercase tracking-[0.2em] text-muted-foreground">
              Demo Dashboard
            </p>
          </div>
        </Link>

        <nav className="flex flex-wrap items-center gap-3">
          <Link to="/dashboard" className={buttonVariants({ variant: 'outline' })}>
            Dashboard
          </Link>
          <a
            href="#features"
            className={buttonVariants({ variant: 'ghost' })}
          >
            Features
          </a>
        </nav>
      </div>
    </header>
  )
}

export default HomeHeader