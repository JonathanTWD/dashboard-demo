import { BrowserRouter, Navigate, Route, Routes } from 'react-router'

import Dashboard from '@/pages/dashboard'
import Home from '@/pages/home'
import { ThemeProvider } from '@/lib/theme'

function App() {
  return (
    <ThemeProvider>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Navigate to={"/home"}/>} />
          <Route path='/home' element={<Home/>}/>
          <Route path="/dashboard" element={<Dashboard />} />
        </Routes>
      </BrowserRouter>
    </ThemeProvider>
  )
}

export default App
