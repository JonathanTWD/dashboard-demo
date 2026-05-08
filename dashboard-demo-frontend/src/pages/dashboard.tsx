import { ArrowLeftIcon, GaugeIcon } from "lucide-react";
import { Link } from "react-router";

import { buttonVariants } from "@/components/ui/button";

function Dashboard() {
  return (
    <main className="min-h-screen bg-background px-6 py-10 text-foreground">
      <div className="mx-auto flex min-h-[calc(100vh-5rem)] w-full max-w-5xl flex-col justify-center gap-8">
        <div className="flex items-center justify-between gap-4">
          <div>
            <p className="text-sm text-muted-foreground">Ruta /dashboard</p>
            <h1 className="text-3xl font-semibold tracking-tight sm:text-4xl">
              Dashboard base
            </h1>
          </div>
          <GaugeIcon className="size-6 text-muted-foreground" />
        </div>

        <section className="grid gap-4 rounded-[2rem] border bg-muted/20 p-6 shadow-sm sm:grid-cols-3">
          {[
            ["Estado", "Listo para conectar datos"],
            ["Router", "React Router v7"],
            ["Data", "React Query activo"],
          ].map(([label, value]) => (
            <article key={label} className="rounded-2xl bg-background p-4">
              <p className="text-sm text-muted-foreground">{label}</p>
              <p className="mt-2 font-medium">{value}</p>
            </article>
          ))}
        </section>

        <div>
          <Link to="/" className={buttonVariants({ variant: "outline" })}>
            <ArrowLeftIcon />
            Volver al home
          </Link>
        </div>
      </div>
    </main>
  );
}

export default Dashboard;
