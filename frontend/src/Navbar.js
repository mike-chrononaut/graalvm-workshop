import React from 'react';
import Select from 'react-select';
import './Navbar.css';

const Navbar = ({userTheme, setUserTheme, fontSize, setFontSize}) => {
    const themes = [
        { value: "vs-dark", label: "Dark" },
        { value: "light", label: "Light" },
    ]
    return (
        <div className="navbar">
            <h1>JS компилятор</h1>
            <Select options={themes} value={userTheme} className='theme-selector'
                    onChange={(e) => setUserTheme(e.value)}
                    placeholder={userTheme} />
            <label>Размер шрифта</label>
            <input type="range" min="18" max="30" className='font-resizer'
                   value={fontSize} step="2"
                   onChange={(e) => { setFontSize(e.target.value)}}/>
        </div>
    )
}

export default Navbar
