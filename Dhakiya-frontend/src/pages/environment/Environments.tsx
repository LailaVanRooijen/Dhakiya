import { SidebarLayout } from "../../components/layouts/sidebarlayout/SidebarLayout";
import { Button } from "../../components/button/Button";
import { LabelBar } from "../../components/labelbar/LabelBar";
import { OverviewList } from "./overviewlist/OverviewList";
import { useEffect, useState } from "react";
import { I_Environment } from "types/api";
import { AxiosClient } from "../../services/AxiosClient";

export const Environments = () => {
    const [environments, setEnvironments] = useState<I_Environment[]>([]);

    const addEnvironment = () => {
        console.log("add");
        // TODO make function to create new environment
    };

    useEffect(() => {
        AxiosClient.get("environments")
            .then((response: I_Environment[]) => setEnvironments(response))
            .catch((error) => {
                console.log(error);
            });
        console.log(environments); // DEBUG STATEMENT, TODO DELETE ME!
    }, []);

    return (
        <div className={` environments-box`}>
            <LabelBar label={"Environments"} />
            <SidebarLayout
                leftContent={
                    <>
                        {environments && (
                            <OverviewList
                                list={environments}
                                linkTo={"environment"}
                            />
                        )}
                    </>
                }
                rightContent={
                    <div>
                        <Button content="add +" handleClick={addEnvironment} />
                    </div>
                }
            />
        </div>
    );
};
