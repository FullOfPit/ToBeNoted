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
import HeaderBar from "../components/HeaderBar";

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
                        <HeaderBar/>
                        <Dashboard/>
                    </Authentication>
                }/>

                <Route path={"/brief"} element={
                    <Authentication>
                        <HeaderBar/>
                        <BriefingDocument/>
                    </Authentication>
                }/>

                <Route path={"/staff"} element={
                    <Authentication>
                        <HeaderBar/>
                        <MemberManagement/>
                    </Authentication>
                }/>

                <Route path={"/archive"} element={
                   <Authentication>
                       <HeaderBar/>
                       <Archive/>
                   </Authentication>
                }/>

            </Routes>
        </ThemeProvider>

    )
}