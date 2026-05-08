function HomeFooter() {
  return (
    <footer className="mx-auto w-full max-w-6xl pt-8">
      <div className="flex flex-col gap-3 rounded-[1.75rem] border bg-white/70 px-5 py-4 text-sm text-muted-foreground shadow-sm backdrop-blur sm:flex-row sm:items-center sm:justify-between">
        <p>JonathanMoreraDev</p>

        <div className="flex items-center gap-3 self-start sm:self-auto">
          <a
            href="https://github.com"
            target="_blank"
            rel="noreferrer"
            aria-label="GitHub"
            className="inline-flex size-10 items-center justify-center rounded-full border bg-white text-foreground transition-colors hover:bg-muted"
          >
            <svg
              viewBox="0 0 24 24"
              aria-hidden="true"
              className="size-5"
              fill="none"
              stroke="currentColor"
              strokeWidth="1.8"
              strokeLinecap="round"
              strokeLinejoin="round"
            >
              <path d="M16 22v-2.3a3.8 3.8 0 0 0-1-2.9c3.3-.4 6.8-1.6 6.8-7.2a5.6 5.6 0 0 0-1.5-3.8 5.2 5.2 0 0 0-.1-3.8s-1.1-.4-3.7 1.4a13 13 0 0 0-6.8 0C6.1 1.8 5 2.2 5 2.2a5.2 5.2 0 0 0-.1 3.8 5.6 5.6 0 0 0-1.5 3.8c0 5.6 3.5 6.8 6.8 7.2a3.8 3.8 0 0 0-1 2.9V22" />
              <path d="M9 18c-4.6 1.5-4.6-2.4-6.4-2.4" />
            </svg>
          </a>
          <a
            href="https://www.linkedin.com"
            target="_blank"
            rel="noreferrer"
            aria-label="LinkedIn"
            className="inline-flex size-10 items-center justify-center rounded-full border bg-white text-foreground transition-colors hover:bg-muted"
          >
            <svg
              viewBox="0 0 24 24"
              aria-hidden="true"
              className="size-5"
              fill="currentColor"
            >
              <path d="M20.45 20.45h-3.56v-5.57c0-1.33-.03-3.05-1.86-3.05-1.86 0-2.14 1.45-2.14 2.95v5.67H9.33V9h3.41v1.56h.05c.48-.9 1.67-1.86 3.43-1.86 3.67 0 4.35 2.42 4.35 5.57v6.18ZM5.34 7.43a2.07 2.07 0 1 1 0-4.14 2.07 2.07 0 0 1 0 4.14ZM7.12 20.45H3.56V9h3.56v11.45Z" />
            </svg>
          </a>
        </div>
      </div>
    </footer>
  )
}

export default HomeFooter