import Login from "./Login";
import {ThemeProvider} from "@mui/material";
import {mainTheme} from "../themes/MainTheme";
import {Route, Routes} from "react-router-dom";
import Dashboard from "./Dashboard";
import NoAuthentication from "../components/NoAuthentication";
import Authentication from "../components/Authentication";
import BriefingDocument from "./BriefingDocument";
import MemberManagement from "./MemberManagement";
import Archive from "./Archive";


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

                <Route path={"/brief"} element={
                    <Authentication>
                        <BriefingDocument/>
                    </Authentication>
                }/>

                <Route path={"/staff"} element={
                    <Authentication>
                        <MemberManagement/>
                    </Authentication>
                }/>

                <Route path={"/archive"} element={
                   <Authentication>
                       <Archive/>
                   </Authentication>
                }/>

            </Routes>
        </ThemeProvider>

    )
}