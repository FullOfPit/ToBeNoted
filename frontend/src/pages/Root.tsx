import Login from "./Login";
import {ThemeProvider} from "@mui/material";
import {mainTheme} from "../themes/MainTheme";
import {Route, Routes} from "react-router-dom";
import Dashboard from "./Dashboard";


export default function Root() {
    
    return (
        <ThemeProvider theme={mainTheme}>
            <Routes>
                <Route path={"/login"} element={
                    <Login/>
                }/>
                <Route path={"/"} element={
                    <Dashboard/>
                }/>
            </Routes>
        </ThemeProvider>

    )
}