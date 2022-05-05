import { useState } from 'react';
import './App.css';
import Editor from "@monaco-editor/react";
import Axios from 'axios';
import spinner from './spinner.png';
import Navbar from "./Navbar";

function App() {

    // State variable to set users source code
    const [userCode, setUserCode] = useState(``);

    // State variable to set editors default theme
    const [userTheme, setUserTheme] = useState("vs-dark");

    // State variable to set editors default font size
    const [fontSize, setFontSize] = useState(20);

    // State variable to set users output
    const [userOutput, setUserOutput] = useState("");

    // Loading state variable to show spinner
    // while fetching data
    const [loading, setLoading] = useState(false);

    const options = {
        fontSize: fontSize
    }

    // Function to call the compile endpoint
    function compile() {
        clearOutput();

        if (userCode === ``) {
            setUserOutput("Пожалуйста, введите код для выполнения");
            return
        }

        setLoading(true);

        // Post request to compile endpoint
        Axios.post(`http://localhost:8080/polyglot/js`, {
            code: userCode })
            .then((res) => {
            setUserOutput(res.data);
        }).then(() => {
            setLoading(false);
        })
            .catch(e => {
                console.log(e);
            })
    }

    // Function to clear the output screen
    function clearOutput() {
        setUserOutput("");
    }

    return (
        <div className="App">
            <Navbar
                userTheme={userTheme} setUserTheme={setUserTheme}
                fontSize={fontSize} setFontSize={setFontSize}
            />
            <div className="main">
                <div className="left-container">
                    <Editor
                        options={options}
                        height="calc(100vh - 50px)"
                        width="100%"
                        theme={userTheme}
                        defaultLanguage="javascript"
                        defaultValue="// Enter your code here"
                        onChange={(value) => { setUserCode(value) }}
                    />
                    <button className="run-btn" onClick={() => compile()}>
                        Запуск
                    </button>
                </div>
                <div className="right-container">
                    <h4>Результат:</h4>
                    {loading ? (
                        <div className="spinner-box">
                            <img src={spinner} alt="Загрузка..." />
                        </div>
                    ) : (
                        <div className="output-box">
                            <pre>{userOutput}</pre>
                            <button onClick={() => { clearOutput() }}
                                    className="clear-btn">
                                Очистить
                            </button>
                        </div>
                    )}
                </div>
            </div>
        </div>
    );
}

export default App;