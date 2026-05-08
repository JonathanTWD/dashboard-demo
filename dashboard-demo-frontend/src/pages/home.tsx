import {
  ArrowRightIcon,
  BarChart3Icon,
  CheckCircle2Icon,
  HelpCircleIcon,
} from "lucide-react";
import { Link } from "react-router";

import { buttonVariants } from "@/components/ui/button";
import HomeFooter from "@/components/shared/Footer";
import HomeHeader from "@/components/shared/Header";

const highlights = [
  { label: "UI base", value: "shadcn + Tailwind" },
  { label: "Data layer", value: "React Query" },
  { label: "Rutas", value: "React Router" },
];

const metrics = [
  { label: "Ventas", value: "$12.4k", delta: "+18%" },
  { label: "Usuarios", value: "1,284", delta: "+6.4%" },
  { label: "Pedidos", value: "392", delta: "+12%" },
  { label: "Conversión", value: "4.8%", delta: "+0.7%" },
];

function Home() {
  return (
    <main className="min-h-screen bg-[radial-gradient(circle_at_top,_rgba(0,0,0,0.08),_transparent_40%),linear-gradient(180deg,_#fafafa_0%,_#f3f4f6_100%)] px-6 py-6 text-foreground sm:py-8">
      <HomeHeader />

      <div className="mx-auto flex min-h-[calc(100vh-18rem)] w-full max-w-6xl items-center py-6 sm:py-8">
        <section className="grid w-full gap-8 lg:grid-cols-[1.1fr_0.9fr]">
          <div className="space-y-6">
            <div className="inline-flex items-center gap-2 rounded-full border bg-white/80 px-4 py-2 text-sm shadow-sm backdrop-blur">
              <CheckCircle2Icon className="size-4" />
              Arquitectura optimizada para producción
            </div>

            <div className="space-y-4">
              <h1 className="max-w-xl text-4xl font-semibold tracking-tight text-balance sm:text-5xl lg:text-6xl">
                Un dashboard limpio, listo para conectar datos reales.
              </h1>
              <p className="max-w-2xl text-base leading-7 text-muted-foreground sm:text-lg">
                Visualiza métricas en tiempo real, organiza tus datos y toma
                decisiones con una interfaz optimizada para rendimiento y
                productividad.
              </p>
            </div>

            <div className="flex flex-wrap gap-3">
              <Link to="/dashboard" className={buttonVariants({ size: "lg" })}>
                Empezar
                <ArrowRightIcon />
              </Link>
              <Link
                to="/dashboard"
                className={buttonVariants({ variant: "outline", size: "lg" })}
              >
                <HelpCircleIcon />
                Documentación
              </Link>
            </div>

            <div className="grid gap-4 sm:grid-cols-3">
              {highlights.map((item) => (
                <article
                  key={item.label}
                  className="rounded-2xl border bg-white/80 p-4 shadow-sm backdrop-blur"
                >
                  <p className="text-sm text-muted-foreground">{item.label}</p>
                  <p className="mt-1 font-medium">{item.value}</p>
                </article>
              ))}
            </div>
          </div>

          <aside className="rounded-[2rem] border bg-white/85 p-6 shadow-[0_20px_60px_rgba(0,0,0,0.08)] backdrop-blur">
            <div className="flex items-center justify-between border-b pb-4">
              <div>
                <p className="text-sm text-muted-foreground">Vista previa</p>
                <h2 className="text-xl font-semibold">Panel de control</h2>
              </div>
              <BarChart3Icon className="size-5 text-muted-foreground" />
            </div>

            <div className="mt-6 grid gap-4 sm:grid-cols-2">
              {metrics.map((item) => (
                <article
                  key={item.label}
                  className="rounded-2xl bg-muted/50 p-4"
                >
                  <p className="text-sm text-muted-foreground">{item.label}</p>
                  <div className="mt-3 flex items-end justify-between gap-4">
                    <p className="text-2xl font-semibold">{item.value}</p>
                    <span className="rounded-full bg-foreground px-2.5 py-1 text-xs font-medium text-background">
                      {item.delta}
                    </span>
                  </div>
                </article>
              ))}
            </div>
          </aside>
        </section>
      </div>

      <HomeFooter />
    </main>
  );
}

export default Home;
