import Login from "./Login";
import {ThemeProvider} from "@mui/material";
import {mainTheme} from "../themes/MainTheme";
import {Route, Routes} from "react-router-dom";
import Dashboard from "./Dashboard";
import NoAuthentication from "../components/NoAuthentication";
import Authentication from "../components/Authentication";


export default function Root() {
    
    return (
        <ThemeProvider theme={mainTheme}>
            <Routes>
                <Route path={"/login"} element={
                    <NoAuthentication>
                        <Login/>
                    </NoAuthentication>
                }/>
                <Route path={"/"} element={
                    <Authentication>
                        <Dashboard/>
                    </Authentication>
                }/>
            </Routes>
        </ThemeProvider>

    )
}