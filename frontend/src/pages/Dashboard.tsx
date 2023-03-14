import * as React from "react";
import {Card, CardContent, Typography} from "@mui/material";

export default function Dashboard() {

    const pageList: { pageName: string, linkTo: string}[] = [
        {pageName: "Briefing Document", linkTo: "/brief"},
        {pageName: "Member Management", linkTo: "/staff"},
        {pageName: "Archive", linkTo: "/archive"}
    ];

    return (
        <div>
            {pageList.map((page) =>
                <Card sx={{maxWidth: 400, minWidth: 275 }}>
                    <CardContent>

                        <Typography variant="h5" component="div">
                            {page.pageName}
                        </Typography>

                        <Typography variant="body2">
                            well meaning and kindly.
                            <br />
                            {'"a benevolent smile"'}
                        </Typography>
                    </CardContent>
                </Card>)}
        </div>
    )
}