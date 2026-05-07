import { BrowserRouter, Navigate, Route, Routes } from 'react-router'

import Dashboard from '@/pages/dashboard'
import Home from '@/pages/home'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Navigate to={"/home"}/>} />
        <Route path='/home' element={<Home/>}/>
        <Route path="/dashboard" element={<Dashboard />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
